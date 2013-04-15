#!/usr/bin/env bash

if [ $# -ne 1 ]; then
    echo "usage: $0 SWIFT_SRC_PATH"
    exit 1
fi

OUT="$1"/java_api

swig -java -package com.oss.teamshare.communication.swift -outdir ../java/com/oss/teamshare/communication/swift -c++ swift.i
mv swift_wrap.cxx swift_wrap.cpp

rm -rf "$OUT"/*
cp * "$OUT"
