// Функция для подтверждения удаления
let transportToDeleteId;

function confirmDelete(transportId) {
    transportToDeleteId = transportId; // Сохраняем ID удаляемого транспорта
    document.getElementById('confirm-modal').style.display = 'block';
}

function cancelDelete() {
    transportToDeleteId = null;
    document.getElementById('confirm-modal').style.display = 'none';
}

async function executeDelete() {
    if (transportToDeleteId) {
        await fetch(`http://192.168.1.201:9090/transport/${transportToDeleteId}`, {
            method: 'DELETE',
        });
        transportToDeleteId = null;
        document.getElementById('confirm-modal').style.display = 'none';
        await fetchClients(); // Обновляем список клиентов после удаления
    }
}
