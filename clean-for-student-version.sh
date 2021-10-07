#!/usr/bin/env bash

git checkout student && \
    ./gradlew clean && \
    rm -rf .gradle .idea/*.xml
