const { SlashCommandBuilder } = require('@discordjs/builders');
const unirest = require('unirest');
require('dotenv').config();

module.exports = {
    data: new SlashCommandBuilder()
        .setName('tpplayer')
        .setDescription('teleporting player to another player on server')
        .addStringOption(option => option.setName('who').setDescription('who will be teleported'))
        .addStringOption(option => option.setName('to').setDescription('to who will be teleported')),
     execute(interaction) {
        const who = interaction.options.get('who').value;
        const to = interaction.options.get('to').value;
        unirest.get(`${process.env.API}/tp-player`).send({
            who: who,
            to: to
        }).then(function(response){
            interaction.reply(response.data);
            console.log(response);
        }).catch( function(error){
            console.log(error);
            interaction.reply("Something went wrong");
          })
        return
    },
};