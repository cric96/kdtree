/*
 * Copyright 2015 The kdtree authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.asoem.kdtree

trait PointValueTuple[+A] extends Product2[HyperPoint, A] {
  def point: HyperPoint
  def value : A
  def dim = point.dim

  override def _1 = point
  override def _2 = value
  override def canEqual(that: Any) = that.isInstanceOf[PointValueTuple[_]]

  override def toString = point.toString
}