const canvas = document.getElementById('synapticCanvas');
const ctx = canvas.getContext('2d');
const stepButton = document.getElementById('step-control');
const playButton = document.getElementById('play-control');

let stepping = false;
let animationInterval = null;

stepButton.addEventListener('click', () => {
    stepGrid();
});

playButton.addEventListener('click', () => {
    if (animationInterval === null) {
        animationInterval = setInterval(stepGrid, 200);
        playButton.textContent = 'Pause';
    } else {
        clearInterval(animationInterval);
        animationInterval = null;
        playButton.textContent = 'Play';
    }
});

async function stepGrid() {
    if (stepping) return;

    stepping = true;

    try {
        const response = await fetch('/step', { method: 'POST' });
        const grid = await response.json();
        drawGrid(grid);
    } finally {
        stepping = false;
    }
}

async function getGrid() {
    const response = await fetch('/grid');
    const grid = await response.json();
    drawGrid(grid);
}

function drawGrid(grid) {
    const cellSize = canvas.width / grid[0].length;

    const colors = ['#111827', '#45dfff', '#a66aff'];

    for (let row = 0; row < grid.length; row++) {
        for (let col = 0; col < grid[row].length; col++) {
            ctx.fillStyle = colors[grid[row][col]];
            ctx.fillRect(col * cellSize, row * cellSize, cellSize, cellSize);
        }
    }
}

getGrid();