@echo off
setlocal

set HOST=xiangcheng.ruankun.xyz
set USER=root
set PASSWORD=@Sicau211
set REMOTE_DIR=/usr/share/nginx/html
set LOCAL_DIR=dist

echo Uploading files to %HOST%...

pscp -r -pw %PASSWORD% "%LOCAL_DIR%\*" %USER%@%HOST%:%REMOTE_DIR%

echo Upload complete.
