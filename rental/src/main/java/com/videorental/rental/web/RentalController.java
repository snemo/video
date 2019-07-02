package com.videorental.rental.web;

import com.videorental.common.rental.OrderId;
import com.videorental.rental.Rental;
import com.videorental.rental.command.RentCommand;
import com.videorental.rental.command.ReturnCommand;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author oleciwoj
 */
@RestController
@RequestMapping("/api/movie")
@AllArgsConstructor
class RentalController {

    private final Rental rental;

    @PostMapping("/rent")
    OrderId rentMovie(@RequestBody RentCommand command) {
        return rental.rentMovie(command);
    }

    @PostMapping("/return")
    OrderId returnMovie(@RequestBody ReturnCommand command) {
        return rental.returnMovie(command);
    }
}
