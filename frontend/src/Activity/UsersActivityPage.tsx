import { useRef, useEffect, useMemo } from 'react';

import { useSelector } from 'react-redux';
import type { RootState } from '../Store/store';
import Chart from '../Clock/Chart';

import './activity.css';

const MonthRenderer = ({ daysCount, monthsMask, startDayOfWeek = 0, monthsIndex = 0 }: 
    { daysCount: number, monthsMask: number, startDayOfWeek?: number, monthsIndex: number }) => {

    const weekArr = useMemo(() => {
        const daysInWeek = 7;
        
        const monthsArr: boolean[] = Array(daysCount).fill(false);
        for(let i = 0; i < daysCount; i++){
            if((monthsMask & (1 << i)) !== 0){
                monthsArr[i] = true;
            }
        }

        const totalWeeks = Math.ceil(monthsArr.length / daysInWeek);

        const newWeekArr: boolean[][] = Array.from({ length: totalWeeks }, (_, weekIndex) => {
            const start = weekIndex * daysInWeek;
            const arrLen = Math.min(daysInWeek, monthsArr.length - start);
            let week = monthsArr.slice(start, start + arrLen);

            while (week.length < arrLen) {
                week.push(false);
            }
            return week;
        });

        return newWeekArr;

    }, [daysCount, monthsMask, startDayOfWeek]);


    return (
        <div className="month">
                <div>{new Date(new Date().getFullYear(), monthsIndex+1, 0).toLocaleString('en-us',{month:'short'})}</div>
                {weekArr.map((singleWeek, i) => {
                   return (
                        <div key={i} className="week">
                            {singleWeek.map((isActive, j) => {
                                return (
                                    <div key={j} className={`day-box ${isActive ? 'active' : 'inactive'}`}>
                                        {isActive && <p className="day-emoji">&#129001;</p>}
                                        {!isActive && <p className="day-emoji">&#11036;</p>}
                                    </div>
                                )
                            })}
                        </div>
                   ); 
                })}
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
    }, [usersActivity]);

    return (
        <div>
            <h2>Yearly Activity Tracker</h2>
            <div className='year'> 
                {monthsDays.current.map((daysCount, monthIndex) => (
                    <MonthRenderer 
                        key={monthIndex} 
                        daysCount={daysCount} 
                        monthsMask={usersActivity.monthMasks[monthIndex]}
                        monthsIndex={monthIndex}
                    />
                ))}
            </div>
            
            <Chart bitMaskArray={usersActivity.monthMasks} timeSpentArray={usersActivity.secondsArray} />

        </div>
    );
}

export default UsersActivityComponent;