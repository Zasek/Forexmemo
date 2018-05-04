package com.spitfire.forexmemo.dao;

import com.spitfire.forexmemo.domain.SecreteModel;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by H.W. on 5/4/2018.
 */
public interface SecreteModelRepository extends MongoRepository<SecreteModel, String> {

    public SecreteModel save(SecreteModel secreteModel);

    public SecreteModel findSecreteModelById(String id);

    public void deleteSecreteModelById(String id);
}
