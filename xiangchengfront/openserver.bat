@echo off
setlocal

set HOST=xiangcheng.ruankun.xyz
set USER=root
set PASSWORD=@Sicau211
set REMOTE_SCRIPT=/root/xiangchengfront/start.sh

echo Running script on %HOST%...

plink -pw %PASSWORD% %USER%@%HOST% "%REMOTE_SCRIPT%"

echo Script execution complete.
