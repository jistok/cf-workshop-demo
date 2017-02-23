#!/bin/bash

set -e -x

pushd cf-workshop-demo
  ./mvnw clean package
#popd
