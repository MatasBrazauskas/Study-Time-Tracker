import { useMemo } from 'react';
import { Line } from 'react-chartjs-2';
import {
  Chart as ChartJS,
  TimeScale, 
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend,
} from 'chart.js';
import 'chartjs-adapter-date-fns'; // Required for TimeScale

ChartJS.register(
  TimeScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend
);

function DailyActivityChart({ bitMaskArray, timeSpentArray }: { bitMaskArray: number[], timeSpentArray: number[] }) {
    
    const { dates, dataPoints } = useMemo(() => {
        const generatedDates = [];
        const generatedData = [];
        const year = new Date().getFullYear();
        
        let timeSpentIndex = 0; 
        for (let month = 0; month < 12; month++) {
            const daysInMonth = new Date(year, month + 1, 0).getDate();
            const bitMask = bitMaskArray[month] || 0;
            
            for (let day = 1; day <= daysInMonth; day++) {
                const currentDate = new Date(year, month, day); 
                generatedDates.push(currentDate);

                const isDayActive = (bitMask & (1 << (day - 1))) !== 0;

                if (isDayActive) {
                    const timeValue = timeSpentArray[timeSpentIndex] || 0;
                    generatedData.push(timeValue);
                    timeSpentIndex++;
                } else {
                    generatedData.push(0);
                }
            }
        }
        
        return { dates: generatedDates, dataPoints: generatedData };
    }, [bitMaskArray, timeSpentArray]);


    const chartData = {
        labels: dates, 
        datasets: [
            {
                label: 'Daily Activity Time Spent',
                data: dataPoints,
                borderColor: 'rgba(75, 192, 192, 0.8)',
                pointRadius: 1,
                borderWidth: 1.5,
                tension: 0.1,
            },
        ],
    };

    const chartOptions = {
        responsive: true,
        maintainAspectRatio: false,
        plugins: {
            title: { display: true, text: 'Full Year Daily Activity' },
            legend: { display: false }
        },
        scales: {
            x: {
                type: 'time', 
                time: {
                    unit: 'month',
                    tooltipFormat: 'MMM d, yyyy',
                    displayFormats: {
                        month: 'MMM yyyy'
                    }
                },
                title: { display: true, text: 'Date' }
            },
            y: {
                beginAtZero: true
            }
        }
    };
    
    // --- Render ---
    return (
        <div style={{ width: '100%', height: '350px' }}>
            <Line options={chartOptions} data={chartData} />
        </div>
    );
}

export default DailyActivityChart;