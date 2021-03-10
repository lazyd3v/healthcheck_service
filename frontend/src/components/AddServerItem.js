import React from "react";

import { MotionBox } from "./motion";

import { cardVariants } from "../animation-configs/grid";

const AddServerItem = ({ layoutId, onClick }) => {
  return (
    <MotionBox
      as="button"
      variants={cardVariants}
      w="100%"
      h="150px"
      layoutId={layoutId}
      variant="outline"
      borderWidth="1px"
      borderRadius="lg"
      onClick={onClick}
    >
      Add new server
    </MotionBox>
  );
};

export default AddServerItem;
