import axios from "./axios";

export const getServers = () => {
  return axios.get("/servers").then(({ data }) => data);
};

export const createServer = (newServerData) => {
  return axios.post("/servers", newServerData).then(({ data }) => data);
};

export const updateServer = (id, serverData) => {
  return axios.put(`/servers/${id}`, serverData).then(({ data }) => data);
};

export const deleteServer = (id) => {
  return axios.delete(`/servers/${id}`).then(({ data }) => data);
};
