package bao.ho.models.sql

import java.time.Instant
import java.util.UUID

case class DynamicProperty(
  id: Int,
  uuid: UUID,
  category: String,
  key: String,
  value: String,
  createdAt: Instant,
  createdBy: Int
)
