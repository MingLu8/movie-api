CREATE ROLE movie_dev_rw WITH LOGIN PASSWORD 'dev_database_passwd';
GRANT ALL PRIVILEGES ON DATABASE movie_db TO movie_dev_rw;