# MongoDB Atlas Setup Guide

This guide walks through setting up a free MongoDB Atlas cluster — the recommended starting point for associates new to MongoDB.

> **Official documentation:** [Get Started with Atlas — MongoDB Docs](https://www.mongodb.com/docs/get-started/)
> Additional reference: [Create a Cluster — MongoDB Atlas Docs](https://www.mongodb.com/docs/atlas/tutorial/create-new-cluster/)

---

## 0. Download Supplementary Tools

I recommend that you install both of the following two tools. Mongo Compass will provide an easier first-time experience.

1. [Install Mongo Shell](https://www.mongodb.com/try/download/shell).
  - Select the appropriate platform from the options available
  - Download and install the downloaded software
  - Confirm installation by running the following command in a terminal: `mongosh --version`

2. [Install Mongo Compass (GUI)](https://www.mongodb.com/try/download/compass)
  - Select the appropriate platform from the options available
  - Download and install the downloaded software

**Note: You will need at least one of these tools installed before you can verify your Mongo Atlas Cluster (Step 6)**

## 1. Create an Atlas Account

1. Go to [mongodb.com/cloud/atlas/register](https://www.mongodb.com/cloud/atlas/register) and create a free account.
2. Sign up using an email address, or sign in with an existing Google/GitHub account.

## 2. Create a Free Cluster (M0)

1. Once logged in, you'll be prompted to create your first cluster (or click **Build a Database** if not).
2. Select the **M0 (Free)** tier. This is sufficient for learning and small projects — no payment information is required.
3. Choose AWS for your cloud provider and select a region. For training purposes, any region close to you is fine.
4. Give your cluster a name (or leave the default, e.g., `Cluster0`).
5. Click **Create Deployment**. Cluster creation typically takes a few minutes.

## 3. Create a Database User

Atlas requires a database-specific username and password, separate from your Atlas account login.

1. When prompted (or under **Database & Network Access** in the left sidebar), choose **Username and Password** as the authentication method.
2. Enter a username and a secure password. **Save these somewhere safe** — you'll need them for your connection string.
3. Click **Create User**.

## 4. Configure Network Access

Atlas blocks all connections by default until you explicitly allow them.

1. Under **Network Access** in the left sidebar, click **Add IP Address**.
2. For training/local development, you can click **Add My Current IP Address**, or use **Allow Access from Anywhere** (`0.0.0.0/0`) — note that allowing access from anywhere is convenient for learning but is **not recommended for production environments**.
3. Click **Confirm**.

## 5. Get Your Connection String

1. Go to **Database Deployments**, find your cluster, and click **Connect**.
2. Choose **Connect your application** (or **Compass**/**Shell**, depending on how you plan to connect).
3. Copy the provided connection string. It will look something like this:

   ```
   mongodb+srv://<username>:<password>@<clustername>.xxxxx.mongodb.net/
   ```

4. Replace `<username>`, `<password>` and/or <clustername> with the appropriate details - note: most of these details should already be automatically filled in.

## 6. Connect and Verify

Using either **mongosh** or **MongoDB Compass**:

- **mongosh:** Paste your connection string into a terminal running `mongosh "<connection string>"`.
- **Compass:** Open Compass, paste the connection string into the connection dialog, and click **Connect**.

Once connected, try creating a test database and inserting a document to confirm everything works end to end.

## Troubleshooting Tips

- **Connection timeout:** Double-check your Network Access IP allowlist — this is the most common issue for beginners.
- **Authentication failed:** Confirm the username and password in your connection string match the database user exactly (this is not your Atlas account login credentials).


## Next Steps

- Review the [Mongo documentation ](https://www.mongodb.com/docs/manual/) for information about the database.