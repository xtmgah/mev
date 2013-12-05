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
package edu.dfci.cccb.mev.hcl.rest.controllers;

import static ch.lambdaj.Lambda.extract;
import static ch.lambdaj.Lambda.on;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.Collection;

import javax.inject.Inject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.dfci.cccb.mev.hcl.domain.contract.Algorithm;
import edu.dfci.cccb.mev.hcl.domain.contract.Metric;

/**
 * @author levk
 * 
 */
@RestController
@ToString
@RequestMapping ("/analysis/hcl")
public class HclAnalyzeController {

  private @Getter @Setter (onMethod = @_ (@Inject)) Collection<Metric> metrics;
  private @Getter @Setter (onMethod = @_ (@Inject)) Collection<Algorithm> algorithms;

  @RequestMapping (value = "/metrics", method = GET)
  public Collection<String> metrics () {
    return extract (metrics, on (Metric.class).name ());
  }

  @RequestMapping (value = "/algorithms", method = GET)
  public Collection<String> algorithms () {
    return extract (algorithms, on (Algorithm.class).name ());
  }
}
