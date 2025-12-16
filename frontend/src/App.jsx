import Visualizer from './components/Visualizer'
import './App.css'

function App() {
  return (
    <div className="w-full min-h-screen bg-gray-900 text-white">
      <header className="p-4 border-b border-gray-700">
        <h1 className="text-2xl font-bold text-center text-blue-400">AlgoVision</h1>
      </header>
      <main className="p-4">
        <Visualizer />
      </main>
    </div>
  )
}

export default App
