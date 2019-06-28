/**
 * Copyright (C) 2015-2019 Philip Helger (www.helger.com)
 * philip[at]helger[dot]com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.helger.as4.messaging.domain;

import java.io.Serializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.helger.as4.soap.ESOAPVersion;
import com.helger.commons.annotation.Nonempty;

/**
 * Base interface for an AS4 message.
 *
 * @author Philip Helger
 */
public interface IAS4Message extends Serializable
{
  /**
   * @return The SOAP version to use. May not be <code>null</code>.
   */
  @Nonnull
  ESOAPVersion getSOAPVersion ();

  /**
   * @return The type of the underlying message. Never <code>null</code>.
   */
  @Nonnull
  EAS4MessageType getMessageType ();

  /**
   * @return The ID of the "Messaging" element for referencing in signing.
   *         Should not be <code>null</code>.
   */
  @Nonnull
  @Nonempty
  String getMessagingID ();

  /**
   * Set the "mustUnderstand" value depending on the used SOAP version.
   *
   * @param bMustUnderstand
   *        <code>true</code> for must understand, <code>false</code> otherwise.
   * @return this for chaining
   */
  @Nonnull
  IAS4Message setMustUnderstand (boolean bMustUnderstand);

  /**
   * Create a SOAP document from this message without a payload.
   *
   * @return The created DOM document
   */
  @Nonnull
  default Document getAsSOAPDocument ()
  {
    return getAsSOAPDocument ((Node) null);
  }

  /**
   * Create a SOAP document from this message with the specified optional
   * payload. Attachments are not handled by this method.
   *
   * @param aPayload
   *        The payload to be added into the SOAP body. May be
   *        <code>null</code>.
   * @return The created DOM document.
   */
  @Nonnull
  Document getAsSOAPDocument (@Nullable Node aPayload);
}
