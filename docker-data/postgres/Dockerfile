## Pull postgres image
FROM postgres:11.4

# Set timezone
ENV TZ=Europe/Berlin

# Copy multiple database initiator script and database dump
COPY ./docker-data/postgres/docker-postgresql-multiple-databases /docker-entrypoint-initdb.d
COPY ./docker-data/postgres/db-dump /tmp
