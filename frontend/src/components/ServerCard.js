import React from "react";

import {
  Heading,
  UnorderedList,
  ListItem,
  Link,
  Text,
  IconButton,
  Box,
} from "@chakra-ui/react";
import { EditIcon, DeleteIcon } from "@chakra-ui/icons";
import formatDistance from "date-fns/formatDistance";
import { useQueryClient } from "react-query";

import { MotionBox } from "./motion";
import { cardVariants } from "../animation-configs/grid";

import { deleteServer } from "../api/servers";

const formatDateTime = (dateString) => {
  return formatDistance(new Date(dateString), new Date(), { addSuffix: true });
};

const ServerCard = ({
  name,
  url,
  status,
  lastChecked,
  createdAt,
  onEditClick,
  layoutId,
  id,
}) => {
  const queryClient = useQueryClient();
  const handleDeleteClick = async () => {
    await deleteServer(id);
    queryClient.invalidateQueries("servers");
  };

  return (
    <MotionBox
      layoutId={layoutId}
      variants={cardVariants}
      borderWidth="1px"
      borderRadius="lg"
      borderColor={status === "AVAILABLE" ? "green" : "red"}
      overflow="hidden"
      p={3}
      position="relative"
    >
      <Heading size="m" as="h3" isTruncated>
        🗄 {name}
      </Heading>
      <Link
        href={url}
        display="block"
        color="gray"
        size="s"
        isTruncated
        isExternal
      >
        {url}
      </Link>

      <UnorderedList styleType="none" mt={2} ml={0}>
        <ListItem>
          Status:{" "}
          <Text as="span" color={status === "AVAILABLE" ? "green" : "red"}>
            {status}
          </Text>
        </ListItem>
        <ListItem>Last checked: {formatDateTime(lastChecked)}</ListItem>
        <ListItem>Created {formatDateTime(createdAt)}</ListItem>
      </UnorderedList>

      <Box position="absolute" top="3" right="3">
        <IconButton onClick={onEditClick} icon={<EditIcon />} display="block" />
        <IconButton
          icon={<DeleteIcon />}
          display="block"
          mt="3"
          onClick={handleDeleteClick}
        />
      </Box>
    </MotionBox>
  );
};

export default ServerCard;
