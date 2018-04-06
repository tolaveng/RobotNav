@echo off

SET filename=RobotNav-test.txt
SET app=.\out\production\RobotNav\ robot.Main

:: Display menu options

:MENU
CLS
echo.
echo Choose Search Strategy
echo .......................
echo.
echo 1 - Depth First Search
echo 2 - Breadth First Search
echo 3 - Greedy Best First Search
echo 4 - A* Star Search
echo 5 - Cust 01
echo 6 - Cust 02
echo.
echo 0 - EXIT
echo.

:: Prompt user input
SET /p menu=Input 1, 2, 3, 4, 5, 6, or 0 then press Enter:

if %menu%==1 GOTO DFS
if %menu%==2 GOTO BFS
if %menu%==3 GOTO GBFS
if %menu%==4 GOTO AS
if %menu%==5 GOTO cust1
if %menu%==6 GOTO cust2
if %menu%==0 GOTO EXIT

:: unknow input goto menu
GOTO MENU

:: Option

:DFS
CLS
echo Depth First Search
java -cp %app% %filename% dfs
pause
GOTO ASK

:BFS
CLS
echo Breadth First Search
java -cp %app% %filename% bfs
pause
GOTO ASK

:GBFS
CLS
echo Greedy Best First Search
java -cp %app% %filename% gbfs
pause
GOTO ASK

:AS
CLS
echo A Star Search
java -cp %app% %filename% as
pause
GOTO ASK

:cust1
CLS
echo Not implement yet
pause
GOTO ASK

:cust2
CLS
echo Not implement yet
pause
GOTO ASK


:ASK
set /p Input=Would you like to run again? (Y/N)
if %Input% == y GOTO MENU


:EXIT
