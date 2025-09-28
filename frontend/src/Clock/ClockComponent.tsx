import { useState, useEffect } from 'react';

function Clock(){
    const [time, setTime] = useState(0);
    const [isRunning, setRunning] = useState(false);

    useEffect(() => {
        let intervalId: number | undefined;

        if (isRunning) {
            intervalId = setInterval(() => {
                setTime(prevTime => prevTime + 1);
            }, 10);
        }

        return () => {
            if (intervalId !== undefined) {
                clearInterval(intervalId);
            }
        };
    }, [isRunning]);

    const hours = Math.floor(time / 360000);
    const minutes = Math.floor((time % 360000) / 6000);
    const seconds = Math.floor((time % 6000) / 100);
    const milliseconds = time % 100;

    const startAndStop = () => {
        setRunning(!isRunning);
    };

    const reset = () => {
        setTime(0);
    };

    return (
        <div>
        <p>
            {hours.toString().padStart(2, "0")}:{minutes.toString().padStart(2, "0")}:
            {seconds.toString().padStart(2, "0")}:
            {milliseconds.toString().padStart(2, "0")}
        </p>
        <div>
            <button onClick={startAndStop}>
            {isRunning ? "Stop" : "Start"}
            </button>
            <button onClick={reset}>Reset</button>
        </div>
        </div>
    );
}

export default Clock;