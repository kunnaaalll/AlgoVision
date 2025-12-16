import { useState, useEffect, useRef } from 'react';

const Visualizer = () => {
  const [array, setArray] = useState([]);
  const [isSorting, setIsSorting] = useState(false);
  const [speed, setSpeed] = useState(50);
  const [algorithm, setAlgorithm] = useState('BUBBLE');
  const timeouts = useRef([]);

  useEffect(() => {
    resetArray();
    return () => clearTimeouts();
  }, []);

  const clearTimeouts = () => {
    timeouts.current.forEach(timeout => clearTimeout(timeout));
    timeouts.current = [];
  };

  const resetArray = () => {
    clearTimeouts();
    setIsSorting(false);
    const newArray = Array.from({ length: 30 }, () => Math.floor(Math.random() * 95) + 5);
    setArray(newArray);
  };

  const runSort = async () => {
    if (isSorting) return;
    setIsSorting(true);

    try {
      const response = await fetch('http://localhost:8080/api/algo/sort', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ algorithm: algorithm, data: array }),
      });

      if (!response.ok) throw new Error('Failed to fetch steps');
      const steps = await response.json();
      animateSteps(steps);

    } catch (error) {
      console.error("Sorting failed:", error);
      setIsSorting(false);
    }
  };

  const animateSteps = (steps) => {
    steps.forEach((step, index) => {
      const timeout = setTimeout(() => {
        if (step.currentArray) {
          setArray(step.currentArray);
        }
        if (index === steps.length - 1) {
          setIsSorting(false);
        }
      }, index * speed);
      timeouts.current.push(timeout);
    });
  };

  const algorithms = [
    { id: 'BUBBLE', label: 'Bubble Sort' },
    { id: 'SELECTION', label: 'Selection Sort' },
    { id: 'INSERTION', label: 'Insertion Sort' },
    { id: 'MERGE', label: 'Merge Sort' }
  ];

  return (
    <div className="flex flex-col items-center gap-8 w-full max-w-4xl px-4">
      {/* Controls Card */}
      <div className="flex flex-col gap-6 p-6 rounded-2xl bg-slate-800/50 backdrop-blur-md border border-slate-700 shadow-xl w-full">
        
        {/* Top Row: Algorithms */}
        <div className="flex flex-wrap justify-center gap-4">
            {algorithms.map((algo) => (
                <button
                    key={algo.id}
                    onClick={() => setAlgorithm(algo.id)}
                    disabled={isSorting}
                    className={`px-4 py-2 rounded-lg font-medium transition-all ${
                        algorithm === algo.id 
                        ? 'bg-cyan-500 text-white shadow-[0_0_15px_rgba(34,211,238,0.5)]' 
                        : 'bg-slate-700 text-slate-300 hover:bg-slate-600'
                    } disabled:opacity-50`}
                >
                    {algo.label}
                </button>
            ))}
        </div>

        {/* Bottom Row: Actions & Settings */}
        <div className="flex flex-wrap items-center justify-center gap-6 pt-4 border-t border-slate-700/50">
            <button 
            onClick={resetArray}
            disabled={isSorting}
            className="px-6 py-2.5 font-semibold text-white bg-gradient-to-r from-red-500 to-pink-600 rounded-lg shadow-lg hover:shadow-red-500/30 hover:scale-105 transition-all disabled:opacity-50"
            >
            Reset Array
            </button>
            
            <button 
            onClick={runSort}
            disabled={isSorting}
            className="px-8 py-2.5 text-lg font-bold text-white bg-gradient-to-r from-emerald-400 to-cyan-500 rounded-lg shadow-lg hover:shadow-cyan-500/30 hover:scale-105 transition-all disabled:opacity-50"
            >
            {isSorting ? 'Sorting...' : 'Start Sorting'}
            </button>

            <div className="flex items-center gap-3 px-4 py-2 rounded-lg bg-slate-900/50 border border-slate-700">
                <span className="text-sm font-medium text-slate-400">Speed</span>
                <input 
                    type="range" 
                    min="0" 
                    max="100" 
                    value={100 - (speed / 5)} 
                    onChange={(e) => {
                        const val = Number(e.target.value);
                        setSpeed((100 - val) * 5 + 10);
                    }}
                    disabled={isSorting}
                    className="w-32 h-2 bg-slate-700 rounded-lg appearance-none cursor-pointer accent-cyan-400"
                />
            </div>
        </div>
      </div>

      {/* Visualizer Area */}
      <div className="relative flex items-end justify-center w-full h-[500px] gap-2 p-8 rounded-3xl bg-slate-900 border border-slate-800 shadow-2xl overflow-hidden">
        {/* Helper grid lines */}
        <div className="absolute inset-0 pointer-events-none opacity-10 bg-[linear-gradient(rgba(255,255,255,0.1)_1px,transparent_1px),linear-gradient(90deg,rgba(255,255,255,0.1)_1px,transparent_1px)] bg-[size:40px_40px]"></div>

        {array.map((value, idx) => (
          <div
            key={idx}
            className="flex-1 rounded-t-md shadow-[0_0_10px_rgba(34,211,238,0.2)] transition-all duration-200 ease-in-out hover:brightness-110"
            style={{ 
              height: `${value}%`,
              background: isSorting 
                ? 'linear-gradient(180deg, #22d3ee 0%, #0891b2 100%)' // Cyan gradient
                : 'linear-gradient(180deg, #34d399 0%, #059669 100%)', // Emerald gradient
            }}
          >
          </div>
        ))}
      </div>
    </div>
  );
};

export default Visualizer;
