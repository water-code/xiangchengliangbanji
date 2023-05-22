#!/bin/bash

cd /root/xiangchengfront/xiangchengliangbanji/xiangchengfront
git pull
pnpm install
pkill -f pnpm
nohup pnpm run dev > nohup.log 2>&1 &