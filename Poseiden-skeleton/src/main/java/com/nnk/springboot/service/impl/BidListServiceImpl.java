package com.nnk.springboot.service.impl;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.service.BidListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BidListServiceImpl implements BidListService {

    @Autowired
    private BidListRepository bidListRepository;

    /**
     *{@inheritDoc}
     */
    @Override
    public List<BidList> findAllBid() {
        return bidListRepository.findAll();
    }
}
