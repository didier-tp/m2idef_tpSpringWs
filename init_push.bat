cd /d "%~dp0"
git init
git add *
git commit -a -m "version initiale"
git remote add gitHubOriginTpSpringWs https://didier-tp:password@github.com/didier-tp/m2idef_tpSpringWs.git
git push -u gitHubOriginTpSpringWs master
pause

REM open with text editor
REM opne with system editor