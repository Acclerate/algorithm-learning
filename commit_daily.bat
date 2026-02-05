@echo off
REM 每日学习完成后，自动提交到 GitHub (Windows)
REM 使用方法: commit_daily.bat "Day N: 完成学习"

setlocal

REM 颜色定义（Windows 10+）
set "GREEN=[92m"
set "BLUE=[94m"
set "YELLOW=[93m"
set "NC=[0m"

echo %BLUE%========================================%NC%
echo %BLUE%  算法学习 - 每日提交脚本%NC%
echo %BLUE%========================================%NC%
echo.

REM 检查是否有提交信息参数
if "%~1"=="" (
    echo %YELLOW%用法: commit_daily.bat "提交信息"%NC%
    echo %YELLOW%示例: commit_daily.bat "Day 1: 完成双指针学习"%NC%
    exit /b 1
)

REM 进入项目目录
cd /d "D:\privategit\github\algorithm-learning" || exit /b 1

echo %GREEN%[1/5] 检查当前状态...%NC%
git status
echo.

echo %GREEN%[2/5] 添加所有更改...%NC%
git add .
echo.

echo %GREEN%[3/5] 创建提交...%NC%
git commit -m "%~1"
echo.

echo %GREEN%[4/5] 推送到 GitHub...%NC%
git push
echo.

echo %GREEN%[5/5] 提交完成！%NC%
echo %BLUE%========================================%NC%
echo %GREEN%今日学习已保存到 GitHub%NC%
echo %GREEN%继续保持，90天后见证蜕变！%NC%
echo %BLUE%========================================%NC%

endlocal
