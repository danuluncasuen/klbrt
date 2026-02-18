const urlParams = new URLSearchParams(window.location.search);
const docId = urlParams.get('docId');
let currentVersion;
let stompClient;

if (!docId) {
    alert('No document specified');
    window.location.href = '/';
}

// 1. Load initial document via REST
fetch(`/api/document/${docId}`)
    .then(response => response.json())
    .then(doc => {
        document.getElementById('editor').value = doc.content;
        currentVersion = doc.version;
        
        // 2. Connect WebSocket after document loads
        connectWebSocket(docId);
    });

function connectWebSocket(docId) {
    const socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    
    stompClient.connect({}, function(frame) {
        console.log('Connected');
        
        // Subscribe to updates
        stompClient.subscribe(`/topic/document/${docId}`, function(message) {
            const change = JSON.parse(message.body);
            
            if (change.error) {
                alert(change.error);
                location.reload();
            } else {
                document.getElementById('editor').value = change.content;
                currentVersion = change.version;
            }
        });
        
        document.getElementById('editor').addEventListener('input', function(event) {
            sendEdit(docId, event.target.value);
        });
    });
}

function sendEdit(docId, content) {
    if (stompClient && stompClient.connected) {
        stompClient.send(`/app/document/${docId}/edit`, {}, 
            JSON.stringify({ 
                content: content, 
                version: currentVersion 
            })
        );
    }
}