#!/usr/bin/env bash

rm -rf ../java/com/oss/teamshare/communication/zerocice/*
slice2java --output-dir ../java/ DeviceEndpoint.ice
