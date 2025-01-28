function formatDate(dateTime) {
    const date = new Date(dateTime);
    const options = { year: 'numeric', month: '2-digit', day: '2-digit', hour: '2-digit', minute: '2-digit', second: '2-digit', hour12: false };
    return date.toLocaleString('ru-RU', options).replace(',', ''); // Замените 'ru-RU' на ваш локальный формат
}

function formatDate2(dateTime) {
    const date = new Date(dateTime);
    const options = { year: 'numeric', month: '2-digit', day: '2-digit', hour12: false };
    return date.toLocaleString('ru-RU', options).replace(',', ''); // Замените 'ru-RU' на ваш локальный формат
}