import React, { useState } from "react";
import { Center, Heading } from "@chakra-ui/react";
import { useQuery } from "react-query";
import { AnimateSharedLayout } from "framer-motion";

import { getServers } from "./api/servers";

import ServerCard from "./components/ServerCard";
import ServerForm from "./components/ServerForm";
import AddServerItem from "./components/AddServerItem";
import { MotionSimpleGrid } from "./components/motion";

const gridVariants = {
  hidden: { opacity: 1, scale: 0 },
  visible: {
    opacity: 1,
    scale: 1,
    transition: {
      delayChildren: 0.3,
      staggerChildren: 0.2,
    },
  },
};

function App() {
  const { data = [] } = useQuery("servers", getServers);

  const [selectedId, setSelectedId] = useState();

  const handleServerCardClick = (id) => () => {
    setSelectedId(id);
  };

  const handleServerFormCloseClick = () => {
    setSelectedId(null);
  };

  return (
    <>
      <Heading mt={3} textAlign="center">
        Healthcheck service
      </Heading>

      <Center>
        {data.length > 0 && (
          <AnimateSharedLayout type="crossfade">
            <MotionSimpleGrid
              variants={gridVariants}
              initial="hidden"
              animate="visible"
              mt={6}
              w="75%"
              columns={3}
              spacing={10}
            >
              {data.map((data) => {
                return (
                  <ServerCard
                    key={data.id}
                    {...data}
                    onEditClick={handleServerCardClick(data.id)}
                    layoutId={data.id}
                  />
                );
              })}

              <AddServerItem
                layoutId="addNewItem"
                onClick={handleServerCardClick("addNewItem")}
              />
            </MotionSimpleGrid>

            {selectedId && (
              <ServerForm
                key={selectedId}
                layoutId={selectedId}
                onClose={handleServerFormCloseClick}
                initialData={
                  selectedId !== "addNewItem"
                    ? data.find(({ id }) => id === selectedId)
                    : undefined
                }
              />
            )}
          </AnimateSharedLayout>
        )}
      </Center>
    </>
  );
}

export default App;
