import { useRef, useEffect } from 'react';

import './userStyle.css';
import { useSelector } from 'react-redux';
import type { RootState } from '../Store/store';

const DayRenderer = ({ daysCount, monthIndex }: { daysCount: number, monthIndex: number }) => {
    const dayElements = Array.from({ length: daysCount }, (_, dayIndex) => (
        <div 
            key={`${monthIndex}-${dayIndex}`} 
            className="day-box"
        >
            {dayIndex + 1}
        </div>
    ));

    return (
        <div className="month-container">
            <h3>Month {monthIndex + 1} ({daysCount} days)</h3>
            <div className="day-grid">
                {dayElements}
            </div>
        </div>
    );
};


function UsersActivityComponent() {
    const monthsDays = useRef<number[]>([]);

    const usersActivity = useSelector((state: RootState) => state.ACTIVITY_SLICES_NAME);

    useEffect(() => {
        const year = new Date().getFullYear();
        const daysArray: number[] = [];

        for (let i = 0; i < 12; i++) {
            daysArray.push(new Date(year, i + 1, 0).getDate());
        }

        monthsDays.current = daysArray;

        console.log(monthsDays.current);
    }, []);

    return (
        <div className='activity'>
            <h2>Yearly Activity Tracker</h2>
            
            {monthsDays.current.map((daysCount, monthIndex) => (
                <DayRenderer 
                    key={monthIndex} 
                    daysCount={daysCount} 
                    monthIndex={monthIndex}
                />
            ))}
        </div>
    );
}

export default UsersActivityComponent;