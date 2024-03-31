// Cria a cena Three.js
var scene = new THREE.Scene();

// Cria a câmera Three.js com visão perspectiva
var camera = new THREE.PerspectiveCamera(75, window.innerWidth / window.innerHeight, 0.1, 1000);

// Cria o renderizador Three.js com suporte a WebGL
var renderer = new THREE.WebGLRenderer();

// Define o tamanho do renderizador para o tamanho da janela do navegador
renderer.setSize(window.innerWidth, window.innerHeight);

// Adiciona o elemento DOM do renderizador à página HTML
document.body.appendChild(renderer.domElement);

// Cria um eixo de coordenadas
const axesHelper = new THREE.AxesHelper(5);

// Define as cores dos eixos de coordenadas
//x - azul y - verde z - vermelho
// axesHelper.setColors(0x0064FF, 0x08FF00, 0xFF0000)
// Adiciona um eixo de coordenadas à cena Three.js        
// scene.add(axesHelper);

const jsonPath = "../../../../resources/jsonGraph.json"
//get the data from the json file
const response = await fetch(jsonPath)
    .then(response => {
        if (!response.ok) {
            throw new Error('Erro ao carregar o arquivo JSON: ' + response.status);
        }

        return response.json();
    })
    .then(data => data);


function drawVertices() {
    //create the vertices
    for (let i = 0; i < response.vertices.length; i++) {
        let vertex = response.vertices[i];
        let geometry = new THREE.SphereGeometry(1, 32, 32);
        let material = new THREE.MeshBasicMaterial({ color: 0xffff00 });
        let sphere = new THREE.Mesh(geometry, material);
        sphere.position.x = vertex.x;
        sphere.position.y = vertex.y;
        sphere.position.z = 0;
        scene.add(sphere);
    }

    //create the edges
    for (let i = 0; i < response.edges.length; i++) {
        let edge = response.edges[i];
        let origin = edge.origin;
        let destination = edge.destination;
        
        let material = new THREE.LineBasicMaterial({ color: 0xffffff });

        const points = [];

        points.push(new THREE.Vector3(response.vertices[origin].x, 
            response.vertices[origin].y, 0));
        
        points.push(new THREE.Vector3(response.vertices[destination].x,
            response.vertices[destination].y, 0));
                
        let geometry = new THREE.BufferGeometry().setFromPoints(points);

        let line = new THREE.Line(geometry, material);
        scene.add(line);
    }
}

drawVertices();


camera.position.z = 25;
camera.position.x = 0;
camera.position.y = 0;

// Define o ponto para onde a câmera está olhando
camera.lookAt(0, 0, 0);

// Define o loop de animação Three.js
function animate() {

    // Solicita a atualização da animação na próxima renderização do navegador
    requestAnimationFrame(animate);

    
    // Renderiza a cena Three.js com a câmera definida
    renderer.render(scene, camera);
}

// Inicia a animação
animate();