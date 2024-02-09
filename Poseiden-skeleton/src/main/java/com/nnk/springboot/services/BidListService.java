package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;

import java.util.List;

public interface BidListService {


    /**
     * Return a list of all the Bids
     * @return List of BidList
     */
    List<BidList> findAllBid();

    /**
     * save the bid in db
     */
     void saveBid(BidList Bid);

    /**
     * find the bid in db with bid id
     *
     * @param id
     * @return
     */
    BidList findByBidListId(Integer id);

    /**
     * Check if ID exists
     *
     * @param id
     * @return
     */
    boolean checkIfIdExists(int id);

    /**
     * Delete the bid in db
     *
     * @param bid
     */
    void deleteBid(BidList bid);
}
