#!/bin/bash

[ -n "$TMUX" ] && unset TMUX

SESSION_NAME="idkTmux"

tmux new-session -d -s $SESSION_NAME

tmux rename-window -t $SESSION_NAME:0 'IDEA'
tmux send-keys -t $SESSION_NAME:0 'cd /opt/jetbrains/idea/bin' C-m
tmux send-keys -t $SESSION_NAME:0 './idea.sh' C-m

tmux new-window -t $SESSION_NAME -n 'Frontend'
tmux send-keys -t $SESSION_NAME:1 'cd ~/Repos/StudyTracker/frontend' C-m
tmux send-keys -t $SESSION_NAME:1 'code .' C-m
tmux send-keys -t $SESSION_NAME:1 'npm run dev' C-m

tmux new-window -t $SESSION_NAME -n 'Backend'
tmux send-keys -t $SESSION_NAME:2 'cd ~/Repos/StudyTracker' C-m

tmux new-window -t $SESSION_NAME -n 'StudyTracker'
tmux send-keys -t $SESSION_NAME:3 'cd ~/Repos/StudyTracker' C-m

tmux attach-session -t $SESSION_NAME
