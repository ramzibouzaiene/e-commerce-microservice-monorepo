#!/bin/bash

cd "$(dirname "$0")" || exit

services=("config-server" "customer" "discovery" "gateway" "notification" "order" "payment" "product")

for service in "${services[@]}"; do
    echo "Building service: $service"
    cd "services/$service" || exit
    mvn clean install jib:build
    cd - || exit
done

echo "All services built successfully."
