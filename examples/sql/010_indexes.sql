/*
 * An index is a separate data structure PostgreSQL maintains
 * alongside a table to locate rows faster, without scanning
 * every row in the table (a "sequential scan").
 *
 * Pros:
 *   - Significantly faster data retrieval on large tables
 *   - Reduces the number of rows PostgreSQL must examine per query
 *   - Essential for columns used frequently in WHERE, JOIN,
 *     and ORDER BY clauses
 *
 * Cons:
 *   - Each index consumes additional disk space
 *   - INSERT, UPDATE, and DELETE are slower because PostgreSQL
 *     must keep every affected index in sync with the table
 *   - Over-indexing a write-heavy table can hurt overall performance
 */


/* CLUSTERED INDEX
 * A clustered index determines the physical order in which rows
 * are stored on disk. Because the rows are stored in index order,
 * range queries and ORDER BY on the indexed column are very
 * efficient — PostgreSQL can read a contiguous block of pages
 * rather than jumping around the table.
 *
 * Important notes:
 *   - A table can only have ONE clustered index, since rows can
 *     only be physically ordered one way
 *   - In PostgreSQL, clustering is not automatic or maintained
 *     continuously. CLUSTER physically reorders the table once
 *     at the time it is run. Subsequent inserts and updates do
 *     not preserve that order — CLUSTER must be re-run periodically
 *     to regain the benefit on a table with frequent writes
 *   - PRIMARY KEY and UNIQUE constraints automatically create a
 *     B-Tree index, which is the typical candidate for clustering
 */
-- This creates a unique index on 'emp_id' to act as our clustered index
CREATE UNIQUE INDEX idx_employees_emp_id
    ON examples.employees (emp_id);

SELECT * FROM examples.employees

-- Using the clustered index we Physically reorder the table rows to match the index order
CLUSTER examples.employees USING idx_employees_emp_id;

SELECT * FROM examples.employees

-- Note: You must re-run CLUSTER periodically on write-active tables to
-- restore physical ordering that has drifted due to inserts/updates
-- i.e. CLUSTER <database> USING <clustered_index>;


/*
 * NON-CLUSTERED INDEX
 * A non-clustered index is a separate structure that stores the
 * indexed column values alongside pointers back to the actual
 * row locations in the table (heap). The physical order of rows
 * on disk is unaffected.
 *
 * A table can have many non-clustered indexes — each one
 * independently optimizes a different query pattern.
 * The trade-off is that every index must be updated on writes,
 * so adding indexes has a cost on INSERT, UPDATE, and DELETE.
 */
-- Non-clustered index on emp_name — speeds up lookups and
-- sorting by employee name without affecting row storage order
CREATE INDEX idx_employees_emp_name
    ON examples.employees (emp_name);

-- Non-clustered index on emp_title — useful when queries
-- frequently filter or group by job title
CREATE INDEX idx_employees_emp_title
    ON examples.employees (emp_title);

-- VERIFY: view all indexes on the employees table
SELECT indexname, indexdef
FROM pg_indexes
WHERE tablename = 'employees'
  AND schemaname = 'examples';


/* ANALYZING INDEX USAGE
 * EXPLAIN ANALYZE shows whether PostgreSQL uses an index or
 * falls back to a sequential scan for a given query.
 * Use this to verify your indexes are being picked up by the
 * query planner, and to identify queries that might benefit
 * from an index that doesn't exist yet.
 */
EXPLAIN ANALYZE
SELECT * FROM examples.employees
WHERE emp_id = 1001;

EXPLAIN ANALYZE
SELECT * FROM examples.employees
WHERE emp_name = 'Alice Johnson';


/* ALTERING AN INDEX
 * PostgreSQL does not support ALTER INDEX for changing the
 * columns or type of an existing index — to change an index
 * definition, drop and recreate it.
 *
 * What ALTER INDEX does support:
 *   - Renaming an index
 *   - Changing index storage parameters (advanced use)
 *
 * SYNTAX: ALTER INDEX <schema_name>.<index_name> RENAME TO <new_name>
 *
 * Note: renaming an index is a metadata-only operation —
 * no table data or index structure is rewritten.
 */
-- Rename an index — useful when naming conventions change
-- or an index was created with a poorly descriptive name
ALTER INDEX examples.idx_employees_emp_name
    RENAME TO idx_employees_emp_name_btree;

-- To change anything beyond the name (columns, type, conditions),
-- drop the old index and create a new one in its place
DROP INDEX IF EXISTS examples.idx_employees_emp_name_btree;

CREATE INDEX idx_employees_emp_name
    ON examples.employees (emp_name);


/* REMOVING AN INDEX
 * DROP INDEX removes the index structure entirely from the
 * database. The table data is unaffected — only the index is removed.
 *
 * Use IF EXISTS to avoid an error if the index does not exist.
 * This is good practice in scripts that may be run more than once.
 *
 * Note: dropping an index on a busy table acquires a lock that
 * blocks writes for the duration. For production environments,
 * DROP INDEX CONCURRENTLY can be used to avoid blocking —
 * it takes longer but does not lock out concurrent writes.
 *
 * SYNTAX: DROP INDEX [IF EXISTS] <index_name>
 *         DROP INDEX CONCURRENTLY [IF EXISTS] <index_name>
 */

-- Drop a single non-clustered index
DROP INDEX IF EXISTS examples.idx_employees_emp_title;

-- Drop the clustered index
-- Note: dropping the clustered index does not reorder the table —
-- rows remain in their last clustered order on disk, they are just
-- no longer maintained by an index going forward
DROP INDEX IF EXISTS examples.idx_employees_emp_id;

-- Drop an index without blocking concurrent writes (production-safe)
DROP INDEX CONCURRENTLY IF EXISTS examples.idx_employees_emp_name;


/*
 * INDEX TYPES  (**YOU DON'T NEED TO KNOW THIS!**)
 *
 * The examples above use the default 'B-Tree' index type, which
 * is the right choice for most use cases. PostgreSQL also
 * supports several specialized index types worth exploring:
 *
 * B-Tree (Balanced Tree - Default)
 *	 Organizes indexed values into a hierarchical tree where:
 *		- The root node sits at the top, and acts as an entry for all lookups
 *		- Branch nodes in the middle store ranges of values that guide searches
 *		- Leaf nodes at the bottom store the actual indexes values alongside
 *		  pointers to corresponding rows in the table.
 *	 The tree is 'balanced' - every leaf node is the same distance from the root
 *	 which guarantees that lookups always take a predictable, consistent number
 *	 of steps no matter how large the table grows.
 *
 * HASH
 *   Stores a hash of the indexed value. Only useful for equality
 *   comparisons (=). Offers no benefit for range queries.
 *   Reference: https://www.postgresql.org/docs/18/indexes-types.html#INDEXES-TYPES-HASH
 *
 * GIN (Generalized Inverted Index)
 *   Designed for columns containing multiple values per row,
 *   such as arrays, JSONB, or full-text search vectors.
 *   Reference: https://www.postgresql.org/docs/18/gin.html
 *
 * GiST (Generalized Search Tree)
 *   A flexible framework supporting geometric types, range types,
 *   and full-text search. Used for nearest-neighbor and overlap queries.
 *   Reference: https://www.postgresql.org/docs/18/gist.html
 *
 * BRIN (Block Range Index)
 *   Stores min/max summary data for ranges of table blocks.
 *   Very small and cheap to maintain — effective on large tables
 *   where column values naturally increase over time, such as
 *   timestamps or auto-incrementing IDs.
 *   Reference: https://www.postgresql.org/docs/18/brin.html
 */