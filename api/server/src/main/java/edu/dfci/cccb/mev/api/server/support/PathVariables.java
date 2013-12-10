/**
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.dfci.cccb.mev.api.server.support;

import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;
import static org.springframework.web.servlet.HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE;

import java.util.Map;

import org.springframework.web.context.request.NativeWebRequest;

/**
 * @author levk
 * 
 */
public final class PathVariables {

  @SuppressWarnings ("unchecked")
  public static String variable (String name, NativeWebRequest request) throws MissingPathVariableException {
    String value = ((Map<String, String>) request.getAttribute (URI_TEMPLATE_VARIABLES_ATTRIBUTE,
                                                                SCOPE_REQUEST)).get (name);
    if (value == null)
      throw new MissingPathVariableException ().name (name);
    return value;
  }

  private PathVariables () {}
}