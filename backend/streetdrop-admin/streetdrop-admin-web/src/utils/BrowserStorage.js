const browserStorage = {
    set: (key, value) => {
        const serializedValue =
            typeof value === 'string' ? value : JSON.stringify(value);
        localStorage.setItem(key, serializedValue);
    },
    get: key => {
        const item = localStorage.getItem(key);
        if (item === null) return null;
        try {
            return JSON.parse(item);
        } catch {
            return item;
        }
    },
    reset: () => {
        localStorage.clear();
    },
    remove: (key) => {
        localStorage.removeItem(key);
    },
};

export default browserStorage;