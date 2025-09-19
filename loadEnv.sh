#!/bin/sh

# Load environment variables from .env file
[ ! -f .env ] || export $(grep -v '^#' .env | xargs)

export DATASOURCE_USER=${DATASOURCE_USER}
export DATASOURCE_PASSWORD=${DATASOURCE_PASSWORD}
export DATASOURCE_URL=${DATASOURCE_URL}