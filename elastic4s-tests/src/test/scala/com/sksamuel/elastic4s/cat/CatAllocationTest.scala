package com.sksamuel.elastic4s.cat

import com.sksamuel.elastic4s.RefreshPolicy
import com.sksamuel.elastic4s.testkit.DockerTests
import org.scalatest.{FlatSpec, Matchers}

class CatAllocationTest extends FlatSpec with Matchers with DockerTests {

  http.execute {
    bulk(
      indexInto("catalloc1/landmarks").fields("name" -> "hampton court palace"),
      indexInto("catalloc2/landmarks").fields("name" -> "hampton court palace"),
      indexInto("catalloc3/landmarks").fields("name" -> "hampton court palace")
    ).refresh(RefreshPolicy.Immediate)
  }.await


  "cats alloc" should "return all shards" in {
    http.execute {
      catAllocation()
    }.await
  }

}
