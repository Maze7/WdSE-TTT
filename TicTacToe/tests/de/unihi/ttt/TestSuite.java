package de.unihi.ttt;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import de.unihi.ttt.model.LogicTests;

/**
 * Test suite for all test cases
 * in tic tac toe project
 * @author marcel
 */
@RunWith(Suite.class)
@SuiteClasses({
    LogicTests.class
})
public class TestSuite {}
