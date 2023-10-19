#!/bin/bash

newman run collection.json -e environment.json -r htmlextra --reporter-htmlextra-displayProgressBar --iteration-count 10 --timeout-request 5000 --timeout-script 2000



