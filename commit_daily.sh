#!/bin/bash
# 每日学习完成后，自动提交到 GitHub
# 使用方法: bash commit_daily.sh "Day N: 完成学习"

# 颜色定义
GREEN='\033[0;32m'
BLUE='\033[0;34m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

echo -e "${BLUE}========================================${NC}"
echo -e "${BLUE}  算法学习 - 每日提交脚本${NC}"
echo -e "${BLUE}========================================${NC}"
echo ""

# 检查是否有提交信息参数
if [ -z "$1" ]; then
    echo -e "${YELLOW}用法: bash commit_daily.sh \"提交信息\"${NC}"
    echo -e "${YELLOW}示例: bash commit_daily.sh \"Day 1: 完成双指针学习，题目 88/27\"${NC}"
    exit 1
fi

# 进入项目目录
cd "D:\privategit\github\algorithm-learning" || exit

echo -e "${GREEN}[1/5] 检查当前状态...${NC}"
git status

echo ""
echo -e "${GREEN}[2/5] 添加所有更改...${NC}"
git add .

echo ""
echo -e "${GREEN}[3/5] 创建提交...${NC}"
git commit -m "$1"

echo ""
echo -e "${GREEN}[4/5] 推送到 GitHub...${NC}"
git push

echo ""
echo -e "${GREEN}[5/5] ✅ 提交完成！${NC}"
echo -e "${BLUE}========================================${NC}"
echo -e "${GREEN}今日学习已保存到 GitHub${NC}"
echo -e "${GREEN}继续保持，90天后见证蜕变！${NC}"
echo -e "${BLUE}========================================${NC}"
