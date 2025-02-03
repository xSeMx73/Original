function createRequest() {
    const modal = document.createElement('div');
    modal.id = 'modal';
    modal.className = 'modal';

    modal.innerHTML = `
        <div class="modal-content">
            <span class="close" onclick="closeModal()">&times;</span>
            <h2>Форма заявки</h2>
            <form id="requestForm">
                <label for="clientName">Наименование клиента:</label>
                <input type="text" id="clientName" name="clientName" required>

                <label for="clientPhone">Номер телефона клиента:</label>
                <input type="tel" id="clientPhone" name="clientPhone" required>

                <label for="transportModel">Модель ТС:</label>
                <input type="text" id="transportModel" name="transportModel" required>

                <label for="transportBrand">Производитель ТС:</label>
                <input type="text" id="transportBrand" name="transportBrand" required>

                <label for="transportYear">Год выпуска ТС:</label>
                <input type="number" id="transportYear" name="transportYear" required>

                <label for="gosNumber">Гос номер ТС:</label>
                <input type="text" id="gosNumber" name="gosNumber" required>

                <label for="mileageStart">Пробег при установке:</label>
                <input type="number" id="mileageStart" name="mileageStart" required>

                <label for="mileageEnd">Пробег при снятии:</label>
                <input type="number" id="mileageEnd" name="mileageEnd" required>

                <label for="vin">VIN номер:</label>
                <input type="text" id="vin" name="vin" required>

                <label for="dateStartRepair">Дата установки запчасти:</label>
                <input type="date" id="dateStartRepair" name="dateStartRepair" required>

                <label for="dateRemovePart">Дата демонтажа запчасти:</label>
                <input type="date" id="dateRemovePart" name="dateRemovePart" required>

                <label for="partArticle">Артикул запчасти:</label>
                <input type="text" id="partArticle" name="partArticle" required>

                <label for="partBrand">Производитель запчасти:</label>
                <input type="text" id="partBrand" name="partBrand" required>

                <label for="partName">Наименование запчасти:</label>
                <input type="text" id="partName" name="partName" required>

                <label for="partDealer">Поставщик запчасти:</label>
                <input type="text" id="partDealer" name="partDealer" required>

                <label for="faultDescription">Описание неисправности:</label>
                <textarea id="faultDescription" name="faultDescription" required></textarea>

                <label for="createManager">Ответственный менеджер:</label>
                <input type="text" id="createManager" name="createManager">

                <input type="hidden" id="createDate" name="createDate">

                <button type="submit">Отправить заявку</button>
            </form>
        </div>
    `;
    document.body.appendChild(modal);
    modal.style.display = "block"; // Показываем модальное окно
}

function closeModal() {
    const modal = document.getElementById('modal');
    if (modal) {
        modal.style.display = "none"; // Скрываем модальное окно
        document.body.removeChild(modal); // Удаляем модальное окно из DOM
    }
}

// обработка формы
document.addEventListener('submit', function(event) {
    if (event.target.id === 'requestForm') {
        event.preventDefault();
        // Обработайте отправку данных формы здесь
        closeModal(); // Закрываем модальное окно после отправки
    }
});