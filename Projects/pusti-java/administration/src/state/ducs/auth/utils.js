export const authHeaderForm = () => {
    const authToken = localStorage.getItem("token");

    if (authToken) {
        return {
            "Content-Type": "multipart/form-data",
            Authorization: "Bearer " + authToken,
        };
    }

    return {};
};