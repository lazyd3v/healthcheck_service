import { Box, SimpleGrid, Button, forwardRef } from "@chakra-ui/react";
import { motion, isValidMotionProp } from "framer-motion";

const createMotionComponent = (Component) =>
  motion.custom(
    forwardRef((props, ref) => {
      const chakraProps = Object.fromEntries(
        Object.entries(props).filter(([key]) => !isValidMotionProp(key))
      );
      return <Component ref={ref} {...chakraProps} />;
    })
  );

export const MotionSimpleGrid = createMotionComponent(SimpleGrid);
export const MotionBox = createMotionComponent(Box);
export const MotionButton = createMotionComponent(Button)
