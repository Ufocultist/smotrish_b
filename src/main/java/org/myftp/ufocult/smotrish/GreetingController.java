package org.myftp.ufocult.smotrish;

import org.myftp.ufocult.smotrish.domain.Channel;
import org.myftp.ufocult.smotrish.repos.ChannelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class GreetingController {
    @Autowired
    private ChannelRepo channelRepo;

    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(name="name", required=false, defaultValue="World") String name,
            Map<String, Object> model
    ) {
        model.put("name", name);
        return "greeting";
    }

    @GetMapping
    public String main(Map<String, Object>model){
        Iterable<Channel> channels = channelRepo.findAll();

        model.put("channels", channels);
        return "main";
    }

    @PostMapping
    public String addChannel(
            @RequestParam String name,
            @RequestParam Integer number,
            Map<String, Object> model
            ){
        Channel channel = new Channel(name, number);
        channelRepo.save(channel);

        Iterable<Channel> channels = channelRepo.findAll();
        model.put("channels", channels);

        return "main";
    }

    @PostMapping("filter")
    public String filter(
            @RequestParam String filter,
            Map<String, Object> model
            ){
                Iterable<Channel> channels;
                if(filter != null && !filter.isEmpty()) {
                    channels = channelRepo.findByName(filter);
                }else {
                    channels = channelRepo.findAll();
                }
                model.put("channels", channels);
            return "main";
    }


}