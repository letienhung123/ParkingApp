function createSpotForms() {
    const level = parseInt(document.getElementById('level').value, 10);
    const container = document.getElementById('spotFormsContainer');
    container.innerHTML = ''; // Clear previous forms

    for (let i = 1; i <= level; i++) {
        // Create a new form for each level
        const form = document.createElement('form');
        form.method = 'post';
        form.action = '/your-endpoint'; // Update this to your server endpoint

        // Create a hidden input to store the level
        const hiddenLevel = document.createElement('input');
        hiddenLevel.type = 'hidden';
        hiddenLevel.name = 'level';
        hiddenLevel.value = i;
        form.appendChild(hiddenLevel);

        // Create label for spot number
        const label = document.createElement('label');
        label.textContent = 'Số bãi cho tầng ' + i + ':';
        form.appendChild(label);

        // Create input for spot number
        const input = document.createElement('input');
        input.type = 'number';
        input.name = 'spotNumber';
        input.min = '1';
        input.required = true;
        form.appendChild(input);

        // Create submit button
        const button = document.createElement('button');
        button.type = 'submit';
        button.textContent = 'Tạo';
        form.appendChild(button);

        // Add the form to the container
        container.appendChild(form);
    }
}
