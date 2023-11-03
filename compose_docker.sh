##!/usr/bin/env bash
#
#GLADIATOR_CLASS="com.tookitaki.gladiator.client.models.AaaFileCopy"
#DIRECTORY_ROOT="sql"
#DOCKER_ROOT="docker/mariadb/sqls"
#
#TDSS_DDL_PATH="ddl/ddl.sql"
#TDSS_DML_PATH="dml/dml.sql"
#
#GLADIATOR_DDL_NAME="1_gladiator_ddl_docker_temp.sql"
#GLADIATOR_DML_NAME="2_gladiator_dml_docker_temp.sql"
#TDSS_GLADIATOR_DDL_NAME="3_tdss_gladiator_dml_docker_temp.sql"
#TDSS_DDL_NAME="4_tdss_ddl_docker_temp.sql"
#TDSS_DML_NAME="5_tdss_dml_docker_temp.sql"
#
#mkdir -p $DOCKER_ROOT
#if [[ "$PWD" == *tdss ]]
#then
#  sbt "common/runMain $GLADIATOR_CLASS /ddl/ddl.sql $DOCKER_ROOT/$GLADIATOR_DDL_NAME"
#  sbt "common/runMain $GLADIATOR_CLASS /dml/dml.sql $DOCKER_ROOT/$GLADIATOR_DML_NAME"
#  sbt "common/runMain $GLADIATOR_CLASS /dml/tdss.sql $DOCKER_ROOT/$TDSS_GLADIATOR_DDL_NAME"
#
#  # Copy any setup DDL and DML
#  cp $DIRECTORY_ROOT/$TDSS_DDL_PATH $DOCKER_ROOT/$TDSS_DDL_NAME
#  cp $DIRECTORY_ROOT/$TDSS_DML_PATH $DOCKER_ROOT/$TDSS_DML_NAME
#
#  # sed usage for portability, see https://stackoverflow.com/a/44864004
#  # removing $mysql_db on the first line from the scripts
#  sed -i.bak '1d' $DOCKER_ROOT/$GLADIATOR_DDL_NAME && rm $DOCKER_ROOT/$GLADIATOR_DDL_NAME.bak
#  sed -i.bak '1d' $DOCKER_ROOT/$GLADIATOR_DML_NAME && rm $DOCKER_ROOT/$GLADIATOR_DML_NAME.bak
#  sed -i.bak '1d' $DOCKER_ROOT/$TDSS_GLADIATOR_DDL_NAME && rm $DOCKER_ROOT/$TDSS_GLADIATOR_DDL_NAME.bak
#  sed -i.bak '1d' $DOCKER_ROOT/$TDSS_DDL_NAME && rm $DOCKER_ROOT/$TDSS_DDL_NAME.bak
#  sed -i.bak '1d' $DOCKER_ROOT/$TDSS_DML_NAME && rm $DOCKER_ROOT/$TDSS_DML_NAME.bak
#  echo 'done'
#else
#  echo "[ERROR] Wrong directory. Run from project root."
#  exit 1
#fi
#
#docker-compose --verbose up -d tdss-mariadb
#
#HOSTNAME="docker.phoenix.host"
#if [ -n "$(grep $HOSTNAME /etc/hosts)" ]
#then
#  echo "[INFO] Hostname already added. Skipping."
#else
#  sudo -- sh -c -e "echo '127.0.0.1      '$HOSTNAME >> /etc/hosts"
#fi
