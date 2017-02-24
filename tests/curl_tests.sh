#!/bin/bash

ADDRESS="http://localhost:8082"

echo "========== Signup =========="
curl -i -X POST $ADDRESS/api/signup -H 'Content-Type: application/json' -d '{"login": "test-user", "password": "test-password", "email": "test_email@test.ru"}'
echo

echo "========== Login =========="
curl -i -X POST $ADDRESS/api/login -H 'Content-Type: application/json' -d '{"login": "vasya", "password": "12345", "email": "vasya@test.ru"}'
echo

echo "========== User-get =========="
curl -i -X POST $ADDRESS/api/user-get
echo

echo "========== User-setting =========="
echo "---------- NOT REALISED"
# curl -i -X POST $ADDRESS/api/user-setting
echo

echo "========== Logout =========="
curl -i -X POST $ADDRESS/api/logout
echo
echo "---------- Should get ERROR"
curl -i -X POST $ADDRESS/api/user-get
echo
