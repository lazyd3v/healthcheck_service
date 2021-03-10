import React from "react";

import {
  FormControl,
  FormLabel,
  FormErrorMessage,
  Input,
  Button,
  IconButton,
  Heading,
  Box,
} from "@chakra-ui/react";
import { CloseIcon } from "@chakra-ui/icons";
import { MotionBox } from "./motion";

import { useForm } from "react-hook-form";
import { useQueryClient } from "react-query";
import { createServer, updateServer } from "../api/servers";

const OverlayWrapper = ({ children }) => {
  return (
    <MotionBox
      position="fixed"
      background="rgba(0, 0, 0, 0.15)"
      top="0"
      left="0"
      width="100vw"
      height="100vh"
      initial="hidden"
      animate="visible"
      variants={{
        hidden: { opacity: 0 },
        visible: { opacity: 1 },
        exit: { opacity: 0 },
      }}
      display="flex"
      alignItems="center"
      justifyContent="center"
    >
      {children}
    </MotionBox>
  );
};

const ServerForm = ({ layoutId, onClose, initialData }) => {
  const queryClient = useQueryClient();
  const { handleSubmit, errors, formState, register, setError } = useForm({
    defaultValues: initialData,
  });

  const onSubmit = async (data) => {
    try {
      if (initialData) {
        await updateServer(initialData.id, data);
      } else {
        await createServer(data);
      }
      onClose();
      queryClient.invalidateQueries("servers");
    } catch (e) {
      if (e.isAxiosError) {
        const { errors: errorsFromResponse = [] } = e.response.data;

        errorsFromResponse.forEach((error) => {
          setError(error.field, { message: error.defaultMessage });
        });
      }
    }
  };

  return (
    <>
      <OverlayWrapper>
        <MotionBox
          layoutId={layoutId}
          borderWidth="1px"
          borderRadius="lg"
          borderColor="black"
          overflow="hidden"
          p={3}
          w="350px"
          h="350px"
          background="#fff"
          initial="hidden"
          animate="visible"
        >
          <Box
            display="flex"
            alignItems="center"
            justifyContent="space-between"
            mb={3}
          >
            <Heading size="l">
              {initialData ? "Update data" : "Add new server"}
            </Heading>

            <IconButton icon={<CloseIcon />} onClick={onClose} />
          </Box>

          <form onSubmit={handleSubmit(onSubmit)}>
            <FormControl isInvalid={errors.name}>
              <FormLabel htmlFor="name">Name</FormLabel>
              <Input name="name" placeholder="name" ref={register()} />
              <FormErrorMessage>{errors.name?.message}</FormErrorMessage>
            </FormControl>

            <FormControl isInvalid={errors.url}>
              <FormLabel htmlFor="name">URL</FormLabel>
              <Input name="url" placeholder="url" ref={register()} />
              <FormErrorMessage>{errors.url?.message}</FormErrorMessage>
            </FormControl>

            <Button
              mt={4}
              colorScheme="teal"
              isLoading={formState.isSubmitting}
              type="submit"
              variant="outline"
            >
              Save
            </Button>
          </form>
        </MotionBox>
      </OverlayWrapper>
    </>
  );
};

export default ServerForm;
