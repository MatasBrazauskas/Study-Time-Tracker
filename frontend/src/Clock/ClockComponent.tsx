import { useState, useEffect } from "react";

import { getYearsActivity } from "../APIs/userActivityAPIs";

function Clock() {
    const [startTime, setStartTime] = useState<number | null>(null);
    const [elapsed, setElapsed] = useState(0);
    const [isRunning, setRunning] = useState(false);

    useEffect(() => {
    let intervalId: number | undefined;

    if (isRunning) {
        if (startTime === null) {
        setStartTime(Date.now() - elapsed);
        }

        intervalId = setInterval(() => {
        setElapsed(Date.now() - (startTime ?? Date.now()));
        }, 10);
    }

    return () => {
        if (intervalId) clearInterval(intervalId);
    };
    }, [isRunning, startTime, elapsed]);

    const hours = Math.floor(elapsed / 3600000);
    const minutes = Math.floor((elapsed % 3600000) / 60000);
    const seconds = Math.floor((elapsed % 60000) / 1000);
    const milliseconds = Math.floor((elapsed % 1000) / 10);

    const startAndStop = () => {
        setRunning(!isRunning);
        if (!isRunning && startTime === null) {
            setStartTime(Date.now() - elapsed);
        }
    };

    const reset = () => {
        setRunning(false);
        setElapsed(0);
        setStartTime(null);
    };

    const endSession = async () => {
        console.log(`This is full year: ${new Date().getFullYear()}`);
        await getYearsActivity(new Date().getFullYear())
    }

    return (
        <div>
            <p>
                {hours.toString().padStart(2, "0")}:
                {minutes.toString().padStart(2, "0")}:
                {seconds.toString().padStart(2, "0")}:
                {milliseconds.toString().padStart(2, "0")}
            </p>
            <div>
                <button onClick={startAndStop}>{isRunning ? "Stop" : "Start"}</button>
                <button onClick={reset}>Reset</button>
                <button onClick={() => endSession()}>End Session</button>
            </div>
        </div>
    );
}

export default Clock;